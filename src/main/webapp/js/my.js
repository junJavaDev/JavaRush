const accountsURL = 'rest/players'
const countAllAccountsURL = accountsURL + '/count'
const selectCountPerPage = document.getElementById('selectCountPerPage')
const selectRace = document.getElementById('selectRace')
const selectProfession = document.getElementById('selectProfession')
const selectBanned = document.getElementById('selectBanned')
const tbody = document.getElementById('tableBody')
const races = ['DWARF', 'ELF', 'GIANT', 'HUMAN', 'ORC', 'TROLL', 'HOBBIT']
const professions = ['CLERIC', 'DRUID', 'NAZGUL', 'PALADIN', 'ROGUE', 'SORCERER', 'WARLOCK', 'WARRIOR']
const banned = ['false', 'true'];
const editImageSrc = 'img/edit.png'
const delImageSrc = 'img/delete.png'
const saveImageSrc = 'img/save.png'
window.pageNumber = 0;
window.countPerPage = 5;

updateTable(window.pageNumber)
drawAccountCreator()

selectCountPerPage.onchange = function () {
    window.countPerPage = selectCountPerPage.options[selectCountPerPage.selectedIndex].value;
    updateTable(0)
}

function drawAccountCreator() {
    selectRace.innerHTML = (createSelect(races).innerHTML)
    selectProfession.innerHTML = (createSelect(professions).innerHTML)
    selectBanned.innerHTML = (createSelect(banned).innerHTML)
    document.getElementById('createAccountForm')
        .addEventListener('submit', submitForm);
}

function submitForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target);
    let body = {};
    formData.forEach((value, key) => {
        body[key] = value;
        if (key === 'birthday') {
            const birthday = document.getElementById("inputBirthday")
            body[key] = birthday.valueAsNumber
        }
    });
    sendRequest('POST', accountsURL, body)
        .then(() => updateTable(window.pageNumber))
    clearInput()
}

function clearInput() {
    const formInput = document.querySelectorAll('#createAccountTable input');
    for (const input of formInput) {
        input.value = "";
    }
    const formSelect = document.querySelectorAll('#createAccountTable select');
    for (const select of formSelect) {
        select.selectedIndex = "0"
    }
}

function updateTable(pageNumber) {
    window.pageNumber = pageNumber;
    getAccountsCount()
        .then(accountsCount => {
            let pages = Math.ceil(accountsCount / window.countPerPage)
            updatePagesButton(pages, window.pageNumber)
            return getAccountsData(window.pageNumber)
        })
        .then(data => drawTable(data))
        .catch(err => console.log(err))
}

function updatePagesButton(pages) {
    const pageButtons = document.getElementById('pageButtons')
    let buttons = [];
    buttons.push(document.createTextNode('Pages:'))
    for (let buttonNumber = 1; buttonNumber <= pages; buttonNumber++) {
        const button = document.createElement('button')
        const buttonText = document.createTextNode('' + buttonNumber);
        if (window.pageNumber + 1 === buttonNumber) {
            button.style.color = "#fc0";
            button.style.fontWeight = "bold";
            button.style.fontSize = "24px"
        }
        button.appendChild(buttonText);
        button.onclick = function () {
            updateTable(buttonNumber - 1)
        };
        buttons.push(button)
    }
    pageButtons.replaceChildren(...buttons)
}

function getAccountsCount() {
    return sendRequest('GET', countAllAccountsURL)
}

function getAccountsData() {
    return sendRequest('GET', getPlayersURLWithParam())
}

function getPlayersURLWithParam() {
    return accountsURL + "?pageNumber=" + window.pageNumber + "&pageSize=" + window.countPerPage
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
    let rows = [];
    for (const rowIndex in data) {
        const row = document.createElement("tr");
        drawRow(row, data, rowIndex)
        rows.push(row)

        function drawRow(row, data, rowIndex) {
            row.innerHTML = ""
            const rowData = data[rowIndex];
            const birthday = new Date(Number(rowData['birthday']))
                .toLocaleString('en', {
                    day: 'numeric',
                    month: 'numeric',
                    year: 'numeric'
                });

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

            tdId.className = "id"
            tdLevel.className = "level"
            tdBirthday.className = "birthday"
            tdEditImage.className = "edit"
            tdDelImage.className = "delete"

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
            editImage.onclick = () => editOnClick()
            delImage.onclick = () => deleteAccount()

            function editOnClick() {
                editImage.src = saveImageSrc
                delImage.hidden = true
                const tdNameEdit = createInputField(tdName.innerText, 'nameEdit')
                const tdTitleEdit = createInputField(tdTitle.innerText, 'titleEdit')
                const tdRaceSelect = createSelect(races, tdRace.innerText, 'raceEdit')
                const tdProfessionSelect = createSelect(professions, tdProfession.innerText, 'professionEdit')
                const tdBannedSelect = createSelect(banned, tdBanned.innerText, 'bannedEdit')
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
                    sendRequest('POST', accountsURL + '/' + id, body)
                        .then(() => getAccountsData(window.pageNumber))
                        .then(data => drawRow(row, data, rowIndex))
                        .catch(err => console.log(err))

                };
            }
            function parseToTextNode(index) {
                return document.createTextNode(rowData[index])
            }
            function deleteAccount() {
                sendRequest('DELETE', accountsURL + '/' + id)
                    .then(() => updateTableAfterDel())
            }
        }



        function updateTableAfterDel() {
            const isLastRow = tbody.childElementCount === 1
            const isNotLastPage = window.pageNumber > 0
            if (isLastRow && isNotLastPage) {
                updateTable(window.pageNumber - 1);
            } else {
                updateTable(window.pageNumber);
            }
        }


    }
    tbody.replaceChildren(...rows);
}


function createSelect(options, selectedOption, id) {
    const select = document.createElement('select')
    select.id = id;
    for (let index = 0; index < options.length; index++) {
        const option = document.createElement('option')
        option.innerText = options[index];
        select.add(option);
        if (options[index] === selectedOption) {
            select.selectedIndex = index
        }
    }
    return select
}

function createInputField(value, id) {
    const input = document.createElement('input');
    input.id = id;
    input.type = 'text'
    input.value = value
    return input
}




