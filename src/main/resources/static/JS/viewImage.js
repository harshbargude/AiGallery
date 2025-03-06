function showImageDetails(element) {
    const prompt = element.getAttribute('data-prompt');
    const model = element.getAttribute('data-model');
    const width = element.getAttribute('data-width');
    const height = element.getAttribute('data-height');
    const imageUrl = element.getAttribute('data-image-url');

    document.getElementById('modal-prompt').textContent = prompt;
    document.getElementById('modal-model').textContent = model;
    document.getElementById('modal-width').textContent = width;
    document.getElementById('modal-height').textContent = height;
    document.getElementById('modal-image').src = imageUrl;
}

function updateDimensions() {
    let selectedValue = document.getElementById("dimensions").value;
    let [width, height] = selectedValue.split("x"); // Split values
    
    document.getElementById("width").value = width;
    document.getElementById("height").value = height;
}