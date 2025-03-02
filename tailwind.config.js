/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./src/main/resources/**/*.{html,js}",
    "./node_modules/flowbite/**/*.js"
  ],
  // content: ["./src/main/resources/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        dark: "#171413", // Change to your preferred dark color
        // lime
        primary: {"50":"#fff7ed","100":"#ffedd5","200":"#fed7aa","300":"#fdba74","400":"#fb923c","500":"#f97316","600":"#ea580c","700":"#c2410c","800":"#9a3412","900":"#7c2d12"}
,
        secondary: "#1a2e1a", // Adjust secondary colors
      },
      backgroundColor: {
        dark: "#1a2e1a", // Background override
      },
    },
  },
  plugins: [
    require('flowbite/plugin')
  ],
  darkMode: "selector",
}

