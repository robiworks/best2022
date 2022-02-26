const express = require('express');
const pg = require('pg');
const bodyParser = require('body-parser');

const app = express();
const port = process.env.PORT || 8080;
app.use(bodyParser.json());

// connect to the database - robi dej zrihti no
const pool = new pg.Pool({
    user: 'aquasystems',
    host: 'database',
    database: 'aquasystems',
    password: 'maribor',
    port: 5432,
});

app.get("/api/list", (req, res) => {
    try {
        // check if user has specified a specific date in the format YYYY-MM-DD
        if (req.query.date) {
            // attempt to parse the date
            const date = new Date(req.query.date);
            // check if the date is valid
            if (date.toString() === "Invalid Date") {
                // if not, return an error
                res.status(400).send("Invalid date format.");
            } else {
                // if it is valid, query the database for the list of items for that date
                pool.query(`SELECT * FROM logs WHERE log_date = '${req.query.date}';`, (err, result) => {
                    if (err) {
                        res.status(500).send(err);
                    } else {
                        // if the query was successful, return the list of items
                        res.status(200).send(result.rows);
                    }
                });
            }
        } else if (req.query.time) {
            // the date should be in format HH:MM:SS
            // validate it
            const time = req.query.time.split(":");
            if (time.length !== 3 && !(time[0] < 24 && time[1] < 60 && time[2] < 60)) {
                res.status(400).send("Invalid time format.");
            } else {
                pool.query(`SELECT * FROM logs WHERE log_time = '${req.query.time}';`, (err, result) => {
                    if (err) {
                        res.status(500).send(err);
                    } else {
                        res.status(200).send(result.rows);
                    }
                })
            }
        } else {
            // just send everything
            pool.query("SELECT * FROM logs;", (err, result) => {
                if (err) {
                    res.status(500).send(err);
                } else {
                    res.status(200).send(result.rows);
                }
            });
        }
    } catch (e) {
        console.log(e);
        res.status(500).send("<h1>500 - Internal Server Error</h1>");
    }
});

app.get("/api/grouped", (req, res) => {
    try {
        pool.query(`SELECT log_date, ROUND(AVG(log_value), 3)
        FROM logs
        GROUP BY DATE(log_date)
        ORDER BY DATE(log_date);`, (err, result) => {
            if (err) {
                res.status(500).send(err);
            } else {
                res.status(200).send(result.rows);
            }
        })
    } catch (e) {
        console.log(e);
        res.status(500).send("<h1>500 - Internal Server Error</h1>");
    }
});

app.put("/api/add", (req, res) => {
    try {
        // query must contain date, time and value
        if (req.body.date && req.body.time && req.body.value) {
            // get the last log entry with log_flag = false
            pool.query(`SELECT * FROM logs WHERE log_flag = false ORDER BY log_id DESC LIMIT 1;`, (err, result) => {
                let lastValue = result.rows[0].log_value;
                // compare it with the new value
                let new_value = req.body.value - lastValue;
                if (new_value < 0 || (new_value >= 3 && new_value <= 10)) {
                    // insert with log_flag = false
                    pool.query(`INSERT INTO logs (log_date, log_time, log_value, log_flag), VALUES ('${req.body.date}', '${req.body.time}', ${req.body.value}, false);`, (err, result) => {
                        res.status(200).send({
                            "success": true,
                            "warning": false
                        });
                    });
                } else {
                    // insert with log_flag = true
                    pool.query(`INSERT INTO logs (log_date, log_time, log_value, log_flag), VALUES ('${req.body.date}', '${req.body.time}', ${req.body.value}, true);`, (err, result) => {
                        res.status(200).send({
                            "success": true,
                            "warning": true
                        });
                    });
                }
            });
        }
        else {
            res.status(400).send("<h1>400 - Invalid request.</h1>");
        }
    } catch (e) {
        console.log(e);
        res.status(500).send("<h1>500 - Internal Server Error</h1>");
    }
});

app.get("/", (req, res) => {
    res.send("<h1>h API</h1>");
});

// start the app
app.listen(port, () => {
    console.log(`Listening on port ${port}`);
});