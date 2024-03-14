document.addEventListener('DOMContentLoaded', function () {
    const contractFilter = document.getElementById('contractFilter');
    const serviceNameFilter = document.getElementById('serviceNameFilter');
    const dateFilter = document.getElementById('dateFilter');
    const typeFilter = document.getElementById('typeFilter');
    const commissionResultFilter = document.getElementById('commissionResultFilter');
    const debtFilter = document.getElementById('debtFilter');

    contractFilter.addEventListener('input', filterTableAndDisplayCount);
    serviceNameFilter.addEventListener('input', filterTableAndDisplayCount);
    dateFilter.addEventListener('input', filterTableAndDisplayCount);
    typeFilter.addEventListener('input', filterTableAndDisplayCount);
    commissionResultFilter.addEventListener('input', filterTableAndDisplayCount);
    debtFilter.addEventListener('input', filterTableAndDisplayCount);

    function filterTableAndDisplayCount() {
        filterTable();
        displayFoundCount();
    }

    function filterTable() {
        const contractValue = contractFilter.value.toUpperCase();
        const serviceNameValue = serviceNameFilter.value.toUpperCase();
        const dateValue = dateFilter.value.toUpperCase();
        const typeValue = typeFilter.value.toUpperCase();
        const commissionResultValue = commissionResultFilter.value.toUpperCase();
        const debtValue = debtFilter.value.toUpperCase();

        const rows = document.getElementById('tableBody').querySelectorAll('tr');

        rows.forEach(row => {
            const cells = row.querySelectorAll('td');
            const contractCell = cells[0].textContent.toUpperCase();
            const serviceNameCell = cells[1].textContent.toUpperCase();
            const dateCell = cells[2].textContent.toUpperCase();
            const typeCell = cells[3].textContent.toUpperCase();
            const commissionResultCell = cells[4].textContent.toUpperCase();
            const debtCell = cells[5].textContent.toUpperCase();

            if (contractCell.indexOf(contractValue) > -1 &&
                serviceNameCell.indexOf(serviceNameValue) > -1 &&
                dateCell.indexOf(dateValue) > -1 &&
                typeCell.indexOf(typeValue) > -1 &&
                commissionResultCell.indexOf(commissionResultValue) > -1 &&
                debtCell.indexOf(debtValue) > -1) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    }

    function displayFoundCount() {
        const rows = document.querySelectorAll('#tableBody tr');
        let visibleRowCount = 0;

        rows.forEach(row => {
            if (window.getComputedStyle(row).display !== 'none') {
                visibleRowCount++;
            }
        });

        document.getElementById('foundCount').textContent = visibleRowCount;
    }

// Вызовем функцию для фильтрации и отображения количества найденных записей
    filterTableAndDisplayCount();
});

document.addEventListener('DOMContentLoaded', function() {
    const addServiceForm = document.getElementById('addServiceForm');
    const submitButton = document.getElementById('submitServiceButton');
    submitButton.addEventListener('click', function(event) {
        event.preventDefault();
        const formData = new FormData(addServiceForm);
        fetch('/add-service', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Ошибка при отправке данных');
                }
                return response.json();
            })
            .then(data => {
                updateTable(data);
            })
            .catch(error => {
                console.error('Ошибка:', error);
            });
    });

    function updateTable(data) {
        const tableBody = document.getElementById('tableBody');
        tableBody.innerHTML = '';

        data.forEach(service => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${service.contract_num}</td>
                <td>${service.service_name}</td>
                <td>${service.date}</td>
                <td>${service.type}</td>
                <td>${service.comission_result}</td>
                <td>${service.debt}</td>
            `;
            tableBody.appendChild(row);
        });

        const foundCountElement = document.getElementById('foundCount');
        const currentCount = parseInt(foundCountElement.textContent);
        foundCountElement.textContent = currentCount + 1;
    }
});
