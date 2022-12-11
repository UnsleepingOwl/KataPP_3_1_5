$(async function () {
    await getCurrentUserTable()
})

async function getCurrentUserTable() {
    fetch("http://localhost:8081/api/user")
        .then(res => res.json())
        .then(data => {

            let rolesSet = data.roles.map(r => r.authority.substring(5)).join(' ')

            $('#user_email')
                .append(data.username)

            $('#user_roles')
                .append(' with roles: ')
                .append(rolesSet)

            $('#user_table')
                .append(
                    `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.firstName}</td>
                <td>${data.lastName}</td>
                <td>${data.age}</td>
                <td>${data.username}</td>
                <td>${rolesSet}</td>
                )`
                )
        })
}

