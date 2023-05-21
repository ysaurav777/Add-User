let addUserFormEl = document.getElementById("addUserForm");
let nameEl = document.getElementById("name");
let emailEl = document.getElementById("email");
let nameErrMsgEl = document.getElementById("nameErrMsg");
let emailErrMsgEl = document.getElementById("emailErrMsg");
let statusEl = document.getElementById("status");
let genderFemaleEl = document.getElementById("genderFemale");
let genderMaleEl = document.getElementById("genderMale");

let createAcc = {
    name: "",
    email: "",
    status: "Active",
    gender: "Male"
};

function createNewAcc(createAcc) {
    let option = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: "Baerer 971f77c0135ddd286dec354f1ea2982929d326efa95e325b81338652e3c78f84",
        },
        body: JSON.stringify(createAcc),
    }

    fetch("https://gorest.co.in/public-api/users", option)
        .then(function(response) {
            return response.json();
        })
        .then(function(jsonText) {
            if (jsonText.code === 422) {
                if (jsonText.Data[0].message === "has already been taken") {
                    emailErrMsgEl.textContent = "Email Already Exists";
                }
            }
        });
}

statusEl.addEventListener("change", function(event) {
    createAcc.status = event.target.value;
});

genderMaleEl.addEventListener("change", function(event) {
    createAcc.gender = event.target.value;
});

genderFemaleEl.addEventListener("change", function(event) {
    createAcc.gender = event.target.value;
});

nameEl.addEventListener("blur", function(event) {
    if (event.target.value === "") {
        nameErrMsgEl.textContent = "Required*";
        nameErrMsgEl.classList.add("error");
    } else {
        nameErrMsgEl.textContent = "";
        createAcc.name = event.target.value;
    }
});

emailEl.addEventListener("blur", function(event) {
    if (event.target.value === "") {
        emailErrMsgEl.textContent = "Required*";
        emailErrMsgEl.classList.add("error");
    } else {
        emailErrMsgEl.textContent = "";
        createAcc.email = event.target.value;
    }
});

function validate(createAcc) {
    if (createAcc.name === "") {
        nameErrMsgEl.textContent = "Required*";
        nameErrMsgEl.classList.add("error");
    } else {
        nameErrMsgEl.textContent = "";
    }

    if (createAcc.email === "") {
        emailErrMsgEl.textContent = "Required*";
        emailErrMsgEl.classList.add("error");
    } else {
        emailErrMsgEl.textContent = "";
    }
}

addUserFormEl.addEventListener("submit", function(event) {
    event.preventDefault();
    validate(createAcc);
    createNewAcc(createAcc);
});