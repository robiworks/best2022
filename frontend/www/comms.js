const URL = "http://api:8080";

const getList = (date) => {
    fetch(URL + "/list")
        .then((res) => alert(res));
}