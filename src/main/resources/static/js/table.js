document.addEventListener('DOMContentLoaded', function () {
    const contractFilter = document.getElementById('contractFilter');
    const serviceNameFilter = document.getElementById('serviceNameFilter');
    const dateFilter = document.getElementById('dateFilter');
    const typeFilter = document.getElementById('typeFilter');
    const commissionResultFilter = document.getElementById('commissionResultFilter');
    const debtFilter = document.getElementById('debtFilter');

    contractFilter.addEventListener('input', filterTable);
    serviceNameFilter.addEventListener('input', filterTable);
    dateFilter.addEventListener('input', filterTable);
    typeFilter.addEventListener('input', filterTable);
    commissionResultFilter.addEventListener('input', filterTable);
    debtFilter.addEventListener('input', filterTable);

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
});