fetch("https://image.pollinations.ai/prompt/", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ prompt: "A sunset over mountains" }) 
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error("Error:", error));
