// Handle form submission to show loading spinner
document.getElementById('generate-form').addEventListener('submit', function () {
    document.getElementById('loading-overlay').classList.remove('hidden');
});

// Update hidden width and height fields based on selected dimensions
function updateDimensions() {
    const select = document.getElementById('dimensions');
    const [width, height] = select.value.split('x');
    document.getElementById('width').value = width;
    document.getElementById('height').value = height;
}

// Ensure dimensions are updated on page load if a value is pre-selected
window.onload = function () {
    updateDimensions();
};

// Assuming your server redirects back after generation, hide the spinner when the page reloads
window.addEventListener('load', function () {
    document.getElementById('loading-overlay').classList.add('hidden');
});

// Function to show image details in modal (unchanged from your code)
function showImageDetails(element) {
    document.getElementById('modal-image').src = element.getAttribute('data-image-url');
    document.getElementById('modal-prompt').textContent = element.getAttribute('data-prompt');
    document.getElementById('modal-model').textContent = element.getAttribute('data-model');
    document.getElementById('modal-width').textContent = element.getAttribute('data-width');
    document.getElementById('modal-height').textContent = element.getAttribute('data-height');
    document.getElementById('image-details-modal').classList.remove('hidden');
}