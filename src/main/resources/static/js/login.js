let formElem = document.querySelector('#form');
let idValue = formElem.username.value;
let pwValue = formElem.password.value;
let buttonElem = formElem.button1;

buttonElem.addEventListener('click', function () {
    fetch('/login',{
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: idValue,
            password: pwValue
        })
    }).then(res => res.json())
    .then(myJson => {

    })
})
