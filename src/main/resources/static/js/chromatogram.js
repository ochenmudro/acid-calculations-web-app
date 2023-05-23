// Получаем блок, в котором находятся поля ввода для концентраций
const concentrationFields = document.getElementById('concentration-fields');

// Получаем блок, в котором находятся поля ввода для площадей пиков
const peakAreaFields = document.getElementById('peak-area-fields');

// Функция для добавления новых полей ввода для концентраций
function addConcentrationField() {
  // Копируем первый блок с полями ввода
  const newField = concentrationFields.children[0].cloneNode(true);

  // Очищаем значение поля ввода
  newField.querySelector('input').value = '';

  // Добавляем новый блок в конец родительского блока
  concentrationFields.appendChild(newField);
}

// Функция для добавления новых полей ввода для площадей пиков
function addPeakAreaField() {
  // Копируем первый блок с полями ввода
  const newField = peakAreaFields.children[0].cloneNode(true);

  // Очищаем значение поля ввода
  newField.querySelector('input').value = '';

  // Добавляем новый блок в конец родительского блока
  peakAreaFields.appendChild(newField);
}
