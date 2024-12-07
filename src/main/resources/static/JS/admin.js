console.log("Admin js")

document.querySelector("#contactImage").addEventListener('change', function(){
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.onload = function(){
        document.getElementById("upload_image_preview").src = reader.result;
        // document
        //     .querySelector("#upload_image_preview")
        //     .setAttribute("src", reader.result);

    }
    reader.readAsDataURL(file);
});