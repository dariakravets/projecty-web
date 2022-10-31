document.getElementById("login").onclick=function () {
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    axios.get('http://localhost:8084/api/users?&email='+email)
        .then(response => console.log(response.data));
}