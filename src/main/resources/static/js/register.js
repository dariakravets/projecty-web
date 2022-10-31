document.getElementById("add").onclick=function () {
    let fName = document.getElementById("fName").value;
    let lName = document.getElementById("lName").value;
    let position = document.getElementById("position").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let matchPassword = document.getElementById("matchPassword").value;
    axios.post('http://localhost:8084/api/users?fName='+fName+'&lName='+lName+'&position='+position+'&email='+email+'&password='+password+'&matchpassword='+matchPassword)
        .then(response => console.log(response.data));
}

