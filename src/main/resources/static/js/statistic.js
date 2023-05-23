const chromatogramsTable = document.getElementById("chromatogramsTable");
const calculationsTable = document.getElementById("calculationsTable");

document.getElementById("showChromatograms").addEventListener("click", function () {
  if (chromatogramsTable.style.display === "block") {
    chromatogramsTable.style.display = "none";
    calculationsTable.classList.add("active");
    chromatogramsTable.classList.remove("active");
  } else {
    chromatogramsTable.style.display = "block";
    calculationsTable.style.display = "none";
    chromatogramsTable.classList.add("active");
    calculationsTable.classList.remove("active");
  }
});

document.getElementById("showCalculations").addEventListener("click", function () {
  if (calculationsTable.style.display === "block") {
    calculationsTable.style.display = "none";
    chromatogramsTable.classList.add("active");
    calculationsTable.classList.remove("active");
  } else {
    calculationsTable.style.display = "block";
    chromatogramsTable.style.display = "none";
    calculationsTable.classList.add("active");
    chromatogramsTable.classList.remove("active");
  }
});

const btnsToggleTable = document.querySelectorAll(".btn-toggle-table");

btnsToggleTable.forEach(btn => {
  btn.addEventListener("click", function () {
    // Переключаем класс "active" у кнопок
    btnsToggleTable.forEach(otherBtn => {
      if (otherBtn !== btn) {
        otherBtn.classList.remove("active");
      }
    });
    btn.classList.toggle("active");

    // Определяем какую таблицу показывать
    const tableId = btn.dataset.table;
    const tableToShow = document.getElementById(tableId);
    const tablesToHide = document.querySelectorAll(
      `[id^="table-"]:not(#${tableId})`
    );

    // Показываем таблицу и скрываем остальные
    tableToShow.style.display = "block";
    tablesToHide.forEach(table => {
      table.style.display = "none";
    });
  });
});


const searchInput = document.getElementById("searchInput");

searchInput.addEventListener("input", function () {
  searchTable();
});
function searchTable() {
  const table = document.querySelector(".table.active");
  const searchText = searchInput.value.toLowerCase();
  const rows = table.querySelectorAll("tbody tr");
  rows.forEach(row => {
    const cells = row.querySelectorAll("td");
    let rowVisible = false;
    cells.forEach(cell => {
      if (cell.textContent.toLowerCase().includes(searchText)) {
        rowVisible = true;
      }
    });
    row.style.display = rowVisible ? "" : "none";
  });
}
