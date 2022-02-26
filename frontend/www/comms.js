const URL = "http://localhost:8080";

// gets every measurement on the specified date
async function getList(date) {
    let res = fetch(URL + "/api/list?date=" + date, {
        method: "GET",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        }
    });
    
    let d = await (await res).json();
    return d;
}
console.log("dkfghsfnkl");

// gets all measurements, grouped and ordered by date
async function getGrouped() {
    let res = await fetch(URL + "/api/grouped", {
        method: "GET",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        }
    });

    let d = await res.json();
    return d;
}

// adds a measurement into the database
async function addMeasurement(date, time, value) {
    let res = fetch(URL + "/api/add?date=" + date + "&time=" + time + "&value=" + value, {
        method: "PUT",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        }
    });

    let d = await (await res).json();
    return d
}