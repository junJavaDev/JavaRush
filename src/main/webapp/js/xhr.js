const accountsURL = '/rest/players'
const countAllAccountsURL = '/rest/players/count'
const selectCountPerPage = document.getElementById('selectCountPerPage')

updateTable(0, 3)


selectCountPerPage.onchange = function () {
    const countPerPage = selectCountPerPage.options[selectCountPerPage.selectedIndex].value;
    updateTable(0, countPerPage)

}

// function setSomeString(newStr) {
//     someString = newStr
//     console.log(someString)
// }


function updateTable(pageNumber, countPerPage) {
    getAccountsCount()
        .then(accountsCount => {
            let pages = Math.ceil(accountsCount / countPerPage)
            updatePagesButton(pages, pageNumber, countPerPage)
            console.log(pages)
            return getAccountsData(pageNumber, countPerPage)
        })
        .then(data => drawTable(data))
        .catch(err => console.log(err))
}

function updatePagesButton(pages, pageNumber, countPerPage) {
    const pageButtons = document.getElementById('pageButtons')
        let buttons = [];
        buttons.push(document.createTextNode('Pages:'))
        for (let buttonNumber = 1; buttonNumber <= pages; buttonNumber++) {
            const button = document.createElement('button')
            const buttonText = document.createTextNode('' + buttonNumber);
            if (pageNumber + 1 === buttonNumber) {
                button.style.background = "darkseagreen";
            }
            button.appendChild(buttonText);
            button.onclick = function () {
                updateTable(buttonNumber - 1, countPerPage)
            };
            buttons.push(button)
        }
        pageButtons.replaceChildren(...buttons)
}

function getAccountsCount() {
    return sendRequest('GET', countAllAccountsURL)
}

function getAccountsData(pageNumber, countPerPage) {
    return sendRequest('GET', getPlayersURLWithParam(pageNumber, countPerPage))
}

function getPlayersURLWithParam(pageNumber, pageSize) {
    return accountsURL + "?" + "pageNumber=" + pageNumber + "&" + "pageSize=" + pageSize
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

function drawTable(data) {
    const tbody = document.getElementById('tableBody')
    let rows = [];
    for (const rowIndex in data) {
        const rowData = data[rowIndex];
        const row = document.createElement("tr");
        const birthday = new Date(rowData['birthday'])
            .toLocaleString('en', {
                day: 'numeric',
                month: 'numeric',
                year: 'numeric'
            });
        row.appendChild(createTd(rowData['id']));
        row.appendChild(createTd(rowData['name']));
        row.appendChild(createTd(rowData['title']));
        row.appendChild(createTd(rowData['race']));
        row.appendChild(createTd(rowData['profession']));
        row.appendChild(createTd(rowData['level']));
        row.appendChild(createTd(birthday));
        row.appendChild(createTd(rowData['banned']));
        rows.push(row)
    }
    tbody.replaceChildren(...rows)
}

function createTd(text) {
    const td = document.createElement("td");
    const cellText = document.createTextNode(text);
    td.appendChild(cellText)
    return td
}