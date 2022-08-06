const accountsURL = 'rest/players'
const countAllAccountsURL = accountsURL + '/count'
const selectCountPerPage = document.getElementById('selectCountPerPage')

const races = [
    'DWARF',
    'ELF',
    'GIANT',
    'HUMAN',
    'ORC',
    'TROLL',
    'HOBBIT',
]

const professions = [
    'CLERIC',
    'DRUID',
    'NAZGUL',
    'PALADIN',
    'ROGUE',
    'SORCERER',
    'WARLOCK',
    'WARRIOR',
]

const banned = [
    'true',
    'false'
];

updateTable(0, 3)

const inputName = document.getElementById('inputName')
const inputTitle = document.getElementById('inputTitle')
let selectRace = document.getElementById('selectRace')
const selectProfession = document.getElementById('selectProfession')
const inputLevel = document.getElementById('inputLevel')
const inputBirthday = document.getElementById('inputBirthday')
const selectBanned = document.getElementById('selectBanned')

selectRace.innerHTML = (createSelect(races).innerHTML)
selectProfession.innerHTML = (createSelect(professions).innerHTML)
selectBanned.innerHTML = (createSelect(banned).innerHTML)




selectCountPerPage.onchange = function () {
    const countPerPage = selectCountPerPage.options[selectCountPerPage.selectedIndex].value;
    updateTable(0, countPerPage)
}


function updateTable(pageNumber, countPerPage) {
    getAccountsCount()
        .then(accountsCount => {
            let pages = Math.ceil(accountsCount / countPerPage)
            updatePagesButton(pages, pageNumber, countPerPage)
            return getAccountsData(pageNumber, countPerPage)
        })
        .then(data => drawTable(data, pageNumber, countPerPage))
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

function deleteAccount(id) {
    console.log(accountsURL + '/' + id)
    return sendRequest('DELETE', accountsURL + '/' + id)
}

function updateTableAfterDel(tbody, pageNumber, countPerPage) {
    const isLastRow = tbody.childElementCount === 1
    const isNotLastPage = pageNumber > 0
    if (isLastRow && isNotLastPage) {
        updateTable(pageNumber - 1, countPerPage);
    } else {
        updateTable(pageNumber, countPerPage);
    }
}

function drawTable(data, pageNumber, countPerPage) {
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

        const editImageSrc = 'img/edit.png'
        const delImageSrc = 'img/delete.png'
        const saveImageSrc = 'img/save.png'

        const editImage = document.createElement('img')
        editImage.src = editImageSrc
        const delImage = document.createElement('img')
        delImage.src = delImageSrc

        const tdId = row.insertCell(0)
        const tdName = row.insertCell(1)
        const tdTitle = row.insertCell(2)
        const tdRace = row.insertCell(3)
        const tdProfession = row.insertCell(4)
        const tdLevel = row.insertCell(5)
        const tdBirthday = row.insertCell(6)
        const tdBanned = row.insertCell(7)
        const tdEditImage = row.insertCell(8)
        const tdDelImage = row.insertCell(9)

        const id = rowData['id']
        tdId.appendChild(document.createTextNode(id))
        tdName.appendChild(parseToTextNode('name'));
        tdTitle.appendChild(parseToTextNode('title'));
        tdRace.appendChild(parseToTextNode('race'));
        tdProfession.appendChild(parseToTextNode('profession'));
        tdLevel.appendChild(parseToTextNode('level'));
        tdBirthday.appendChild(document.createTextNode(birthday));
        tdBanned.appendChild(parseToTextNode('banned'));
        tdEditImage.appendChild(editImage);
        tdDelImage.appendChild(delImage);
        editImage.onclick = function () {
            editImage.src = saveImageSrc


            delImage.hidden = true

            const tdNameEdit = createInputField(tdName.innerText)
            const tdTitleEdit = createInputField(tdTitle.innerText)
            const tdRaceSelect = createSelect(races, tdRace.innerText)
            const tdProfessionSelect = createSelect(professions, tdProfession.innerText)
            const tdBannedSelect = createSelect(banned, tdBanned.innerText)

            tdName.replaceChild(tdNameEdit, tdName.firstChild)
            tdTitle.replaceChild(tdTitleEdit, tdTitle.firstChild)
            tdRace.replaceChild(tdRaceSelect, tdRace.firstChild);
            tdProfession.replaceChild(tdProfessionSelect, tdProfession.firstChild);
            tdBanned.replaceChild(tdBannedSelect, tdBanned.firstChild);

            editImage.onclick = function () {
                const body = {
                    name: tdNameEdit.value,
                    title: tdTitleEdit.value,
                    race: tdRaceSelect.value,
                    profession: tdProfessionSelect.value,
                    banned: tdBannedSelect.value
                }
                console.log(body)
                sendRequest('POST', accountsURL + '/' + id, body)
                    .then(() => updateTable(pageNumber, countPerPage))
            };

        };

        delImage.onclick = function () {
            console.log(id);
            deleteAccount(id)
                .then(() => updateTableAfterDel(tbody, pageNumber, countPerPage))
        };


        rows.push(row)

        function parseToTextNode(index) {
            return document.createTextNode(rowData[index])
        }




    }
    tbody.replaceChildren(...rows);



}

function createSelect(options, selectedOption) {
    const select = document.createElement('select')
    for (let index = 0; index < options.length; index++) {
        const option = document.createElement('option')
        option.innerText = options[index];
        select.add(option);
        console.log("opt index = " + options[index] + "selected opt" + selectedOption)
        if (options[index] === selectedOption) {
            select.selectedIndex = index
        }
    }


    // for (const textOption of options) {
    //     const option = document.createElement('option')
    //     option.innerText = textOption;
    //     if (textOption === 'ORC') {
    //         select. = 'selected'
    //     }
    //     select.add(option);
    // }
    return select
}

function createInputField(value) {
    const input = document.createElement('input');
    input.type = 'text'
    input.value = value
    return input
}




