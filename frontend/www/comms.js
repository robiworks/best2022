const URL = "http://localhost:8080";

// gets every measurement on the specified date
const getList = (date) => {
    fetch(URL + "/api/list?date=" + date, {
        method: "GET",
        mode: "cors"
    }).then((res) => console.log(res));
}

// gets all measurements, grouped and ordered by date
const getGrouped = () => {
    fetch(URL + "/api/grouped", {
        method: "GET",
        mode: "cors"
    }).then((res) => console.log(res));
}

// adds a measurement into the database
const addMeasurement = (date, time, value) => {
    fetch(URL + "/api/add?date=" + date + "&time=" + time + "&value=" + value, {
        method: "GET",
        mode: "cors"
    }).then((res) => console.log(res));
}