const requestListPlayersURL = '/rest/players'

sendRequest('GET', requestListPlayersURL)
    .then(data => console.log(data))
    .catch(err => console.log(err))


function sendRequest(method, url, body) {
    const headers = {
        'Content-Type': 'application/json'
    }
    return fetch(url, {
        method: method,
        body: JSON.stringify(body),
        headers: headers
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
        return response.json.then(error => {
            const e = new Error ('Что-то пошло не так')
            e.data = error
            throw e
        });
    });
}