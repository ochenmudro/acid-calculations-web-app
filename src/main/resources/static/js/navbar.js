const checkbox = document.getElementById('dark-mode');
const body = document.body;
const btnTable1 = document.getElementById('showChromatograms');
const btnTable2 = document.getElementById('showCalculations');

checkbox.addEventListener('change', function () {
    if (this.checked) {
        body.classList.add('dark-mode');
        btnTable1.classList.add('dark-mode');
        btnTable2.classList.add('dark-mode');
    } else {
        body.classList.remove('dark-mode');
        btnTable1.classList.remove('dark-mode');
        btnTable2.classList.remove('dark-mode');
    }
});

const themeSwitch = document.getElementById('dark-mode');

// Сохраняем текущую тему в localStorage
function saveTheme(theme) {
    localStorage.setItem('theme', theme);
}

// Получаем сохраненную тему из localStorage
function getSavedTheme() {
    return localStorage.getItem('theme');
}

// Обновляем тему и сохраняем ее в localStorage
function updateTheme() {
    if (themeSwitch.checked) {
        document.body.classList.add('dark-mode');
        btnTable1.classList.add('dark-mode');
        btnTable2.classList.add('dark-mode');
        saveTheme('dark');
    } else {
        document.body.classList.remove('dark-mode');
        btnTable1.classList.remove('dark-mode');
        btnTable2.classList.remove('dark-mode');
        saveTheme('light');
    }
}

// Установка сохраненной темы при загрузке страницы
const savedTheme = localStorage.getItem('theme');
if (savedTheme === 'dark') {
    document.body.classList.add('dark-mode');
    themeSwitch.checked = true;
    btnTable1.classList.add('dark-mode');
    btnTable2.classList.add('dark-mode');
} else {
    body.classList.remove('dark-mode');
    btnTable1.classList.remove('dark-mode');
    btnTable2.classList.remove('dark-mode');
}

// Обновляем тему при изменении переключателя
themeSwitch.addEventListener('change', updateTheme);