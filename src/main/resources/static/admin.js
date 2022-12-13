$(async function () {
    await getAllUsersTable()
})

$(async function () {
    await createUser()
})

$(async function () {
    await updateUser()
})

$(async function () {
    await deleteUser()
})

// ALL USERS
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
                            id="button_edit" data-action="edit" data-id="${user.id}" data-target="#modal_edit">
                            Edit</button>
                            </td>
                            <!-- |ADMIN| |ALL USERS| END OF EDIT BUTTON-->
                            
                            <!-- |ADMIN| |ALL USERS| DELETE BUTTON-->
                            <td>
                            <button type="button" class="btn btn-sm btn-danger" data-toggle="modal"
                            id="button_delete" data-action="delete" data-id="${user.id}" data-target="#modal_delete">
                            Delete</button>
                            </td>
                            <!-- |ADMIN| |ALL USERS| END OF DELETE BUTTON-->
                        </tr>)`
                )
            })
        })
}
// END OF ALL USERS

// NEW USER
async function createUser() {

    const newForm = document.forms["form_new_user"]
    newForm.addEventListener("submit", addNewUser)

    function addNewUser(event) {
        event.preventDefault()
        let userRoles = []
        if (newForm.newUserRoles !== undefined) {
            for (let i = 0; i < newForm.newUserRoles.options.length; i++) {
                if (newForm.newUserRoles.options[i].selected) userRoles.push({
                    id: newForm.newUserRoles.options[i].value,
                    authority: newForm.newUserRoles.options[i].authority
                })
            }
        }

        fetch("http://localhost:8081/api/admin/new", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                firstName: newForm.firstName.value,
                lastName: newForm.lastName.value,
                age: newForm.age.value,
                username: newForm.username.value,
                password: newForm.password.value,
                roles: userRoles
            })
        })
            .then(() => {
                newForm.reset()
                getAllUsersTable()
                $("#all_users_tab").click()
            })
    }

    await allRoles.then(roles => {
        roles.forEach(role => {
            let option = document.createElement("option")
            option.text = role.authority.substring(5)
            option.value = role.id
            $("#new_roles")[0].appendChild(option)
        })
    })
}
// END OF NEW USER

// MODAL EDIT
$("#modal_edit").on("show.bs.modal", event => {
    let button = $(event.relatedTarget)
    let id = button.data("id")
    showModal(id, "form_edit", "#edit_roles")
})
// END OF MODAL EDIT

// UPDATE USER
async function updateUser() {

    const editForm = document.forms["form_edit"]
    const editButton = document.getElementById("edit_confirm")

        editButton.onclick = function editUser(event) {
        event.preventDefault()
        let userRoles = []
        if (editForm.editUserRoles !== undefined) {
            for (let i = 0; i < editForm.editUserRoles.options.length; i++) {
                if (editForm.editUserRoles.options[i].selected) userRoles.push({
                    id: editForm.editUserRoles.options[i].value,
                    role: "ROLE_" + editForm.editUserRoles.options[i].text
                })
            }
        }

        fetch("http://localhost:8081/api/admin/edit", {
            method: "PATCH",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                id: editForm.id.value,
                firstName: editForm.firstName.value,
                lastName: editForm.lastName.value,
                age: editForm.age.value,
                username: editForm.username.value,
                password: editForm.password.value,
                roles: userRoles
            })
        }).then(() => {
            $("#edit_close").click()
            getAllUsersTable()
        })
    }
}
// END OF UPDATE USER


// MODAL DELETE
$("#modal_delete").on("show.bs.modal", event => {
    let button = $(event.relatedTarget)
    let id = button.data("id")
    showModal(id, "form_delete", "#delete_roles")
})
// END OF MODAL DELETE

// DELETE USER
async function deleteUser() {

    const deleteForm = document.forms["form_delete"]
    const deleteButton = document.getElementById("delete_confirm")

    deleteButton.onclick = function deleteUser(event) {

        event.preventDefault()

        fetch("http://localhost:8081/api/admin/id=" +deleteForm.id.value, {
            method: "DELETE",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                id: deleteForm.id.value,
                firstName: deleteForm.firstName.value,
                lastName: deleteForm.lastName.value,
                age: deleteForm.age.value,
                username: deleteForm.username.value,
                password: deleteForm.password.value,
                roles: allRoles
            })
        }).then(() => {
            $("#delete_close").click()
            getAllUsersTable()
        })
    }
}
// END OF DELETE USER


async function showModal(id, formId, rolesId) {
    let user = await getUserById(id)
    let form = document.forms[formId]
    form.id.value = user.id
    form.firstName.value = user.firstName
    form.lastName.value = user.lastName
    form.age.value = user.age
    form.username.value = user.username
    form.password.value = ""

    await allRoles.then(roles => {
        roles.forEach(role => {
            let selectedRole = false
            for (let i = 0; i < user.roles.length; i++) {
                if (user.roles[i].role === role.role) {
                    selectedRole = true
                    break
                }
            }
            let option = document.createElement("option")
            option.text = role.authority.substring(5)
            option.value = role.id
            $(rolesId)[0].appendChild(option)
        })
    })
}

async function getUserById(id) {
    let url = "http://localhost:8081/api/admin/id=" + id
    let response = await fetch(url)
    return await response.json()
}

let allRoles = fetch("http://localhost:8081/api/admin/roles")
    .then(res => res.json())

