const URL = "http://localhost:8080";

// gets every measurement on the specified date
const getList = (date) => {
    fetch(URL + "/api/list?date=fixme", {
        method: "GET",
        mode: "cors"
    }).then((res) => alert(res));
}

// gets all measurements, grouped and ordered by date
const getGrouped = () => {
    fetch(URL + "/api/grouped", {
        method: "GET",
        mode: "cors"
    }).then((res) => alert(res));
}