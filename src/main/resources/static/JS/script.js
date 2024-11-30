console.log("Script loaded");

let currentTheme = getTheme();

// Initially set theme on DOM load
document.addEventListener("DOMContentLoaded", () => {
    changeTheme();
});

// Change theme functionality
function changeTheme() {
    const changeThemeButton = document.querySelector("#theme_change_button");
    if (!changeThemeButton) return;

    // Apply the theme to the page
    updatePageTheme(currentTheme);

    // Set button text based on current theme
    changeThemeButton.querySelector("span").textContent =
        currentTheme === "light" ? "dark" : "light";

    // Listener to toggle theme
    changeThemeButton.addEventListener("click", () => {
        console.log("Theme change button clicked.");

        // Toggle between dark and light themes
        currentTheme = currentTheme === "dark" ? "light" : "dark";
        console.log("New theme:", currentTheme);

        // Update the page theme and local storage
        updatePageTheme(currentTheme);
    });
}

// Save theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Retrieve theme from local storage
function getTheme() {
    return localStorage.getItem("theme") || "light";
}

// Update the theme on the page
function updatePageTheme(theme) {
    const html = document.querySelector("html");
    html.classList.remove("light", "dark"); // Remove any existing theme
    html.classList.add(theme); // Apply the new theme

    // Update local storage and button text
    setTheme(theme);
    document.querySelector("#theme_change_button span").textContent =
        theme === "light" ? "dark" : "light";
}
