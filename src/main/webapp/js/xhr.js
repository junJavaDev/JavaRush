const requestListPlayersURL = '/rest/players'
const requestAllAccountsURL = '/rest/players/count'
const selectCountPerPage = document.getElementById('selectCountPerPage')

sendRequest('GET', requestListPlayersURL)
    .then(data => createTable(data))
    .catch(err => console.log(err))

selectCountPerPage.onchange = function () {
    console.log(getAccountsCount())
    selectCountPerPage.options[selectCountPerPage.selectedIndex].value
}


function getAccountsCount() {
    const countPromise = sendRequest('GET', requestAllAccountsURL)
    return countPromise.then()
}

function sendRequest(method, url, body = null) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest()
        xhr.open(method, url)
        xhr.responseType = 'json'
        xhr.setRequestHeader('Content-Type', 'application/json')
        xhr.onload = () => {
            if (xhr.status >= 400) {
                reject(xhr.response);
            } else {
                resolve(xhr.response);
            }
        }
        xhr.onerror = () => {
            reject(xhr.response)
        }
        xhr.send(JSON.stringify(body))
    });
}

function createTable(data) {
    const body = document.getElementsByTagName("body")[0];
    const table = document.getElementById('table');
    const tbody = document.createElement('tbody');
    for (const dataKey in data) {
        const rowObject = data[dataKey];
        const row = document.createElement("tr");
        for (const key in rowObject) {
            const td = document.createElement("td");
            const cellText = document.createTextNode(rowObject[key]);
            td.appendChild(cellText);
            row.appendChild(td);
        }
        tbody.appendChild(row);
    }
    body.appendChild(table);
    table.appendChild(tbody);
}