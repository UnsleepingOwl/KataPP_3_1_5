$(async function () {
    await getAllUsersTable();
});

$(async function () {
    await createUser();
});


async function getAllUsersTable() {

    $("#all_users_table").empty()

    fetch("http://localhost:8081/api/admin/all")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                $("#all_users_table").append(
                    `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>   
                            <td>${user.age}</td>                      
                            <td>${user.username}</td>
                            <td>${user.roles.map(r => r.authority.substring(5)).join(" ")}</td>
                            
                            <!-- |ADMIN| |ALL USERS| EDIT BUTTON-->
                            <td>
                            <button type="button" class="btn btn-sm btn-info" data-toggle="modal"
                            id="button_edit" data-action="edit" data-id="${user.id}" data-target="#edit">
                            Edit</button>
                            </td>
                            <!-- |ADMIN| |ALL USERS| END OF EDIT BUTTON-->
                            
                            <!-- |ADMIN| |ALL USERS| DELETE BUTTON-->
                            <td>
                            <button type="button" class="btn btn-sm btn-danger" data-toggle="modal"
                            id="button_delete" data-action="delete" data-id="${user.id}" data-target="#delete">
                            Delete</button>
                            </td>
                            <!-- |ADMIN| |ALL USERS| END OF DELETE BUTTON-->
                        </tr>)`
                )
            })
        })
}


async function createUser() {

    const newUserForm = document.forms["form_new_user"]
    newUserForm.addEventListener("submit", addNewUser)

    function addNewUser(event) {

        event.preventDefault()

        let userRoles = []
        if (newUserForm.newUserRoles !== undefined) {
            for (let i = 0; i < newUserForm.newUserRoles.options.length; i++) {
                if (newUserForm.newUserRoles.options[i].selected) userRoles.push({
                    id: newUserForm.newUserRoles.options[i].value,
                    authority: newUserForm.newUserRoles.options[i].authority
                })
            }
        }

        fetch("http://localhost:8081/api/admin/new", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                firstName: newUserForm.firstName.value,
                lastName: newUserForm.lastName.value,
                age: newUserForm.age.value,
                username: newUserForm.username.value,
                password: newUserForm.password.value,
                roles: userRoles
            })
        })
            .then(() => {
                newUserForm.reset();
                getAllUsersTable();
                $("#all_users_tab").click();
            })
    }

    await fetch("http://localhost:8081/api/admin/roles")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(r => {
                let option = document.createElement("option")
                option.text = r.authority.substring(5)
                option.value = r.id
                $("#new_roles")[0].appendChild(option);
            })
        })
}


