document.getElementById('loginButton').addEventListener("click", function (event) {
    event.preventDefault();
    let inputValue = document.getElementById('textInput').value;
    fetch('/login' , {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'LOGIN:' + encodeURIComponent(inputValue)
    }).then(response => {
        document.cookie = response.headers.get("Set-Cookie");
    })
        .then(data => console.log(data))
        .catch(error => console.error('Error: ',error));
})

document.getElementById('redirection').addEventListener("click", event => {
    event.preventDefault();
    fetch('/login',{
        method: 'GET',
        headers:{},
        body:{}
    }).then(response => response.text())
        .then(data => console.log(data))
        .catch(error => console.error(error));
})