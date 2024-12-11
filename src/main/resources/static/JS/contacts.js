console.log("contacts.js");
let baseUrl = "http://localhost:8081/api/contacts";
let usercontrollerUrl = "http://localhost:8081/user/contacts";
const ViewContactModal = document.getElementById("view_contact_modal");



// options with default values
const options = {
    placement: "bottom-right",
    backdrop: "dynamic",
    backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
    closable: true,
    onHide: () => {
        console.log("modal is hidden");
    },
    onShow: () => {
        console.log("modal is shown");
    },
    onToggle: () => {
        console.log("modal has been toggled");
    },
};

// instance options object
const instanceOptions = {
    id: "view_contact_modal",
    override: true,
};

const ContactModal = new Modal(ViewContactModal, options, instanceOptions);
function openContactModal() {
    ContactModal.show();
}

function hideContactModal() {
    ContactModal.hide();
}

async function lodeContactData(id) {
    console.log(id);
    try {
        const data = await (await fetch(`${baseUrl}/${id}`)).json();
        console.log(data);
        // console.log(data.name)
        document.querySelector("#view_contact_name").innerHTML = data.name;
        document.querySelector("#view_contact_email").innerHTML = data.email;
        document.querySelector("#view_contact_phone").innerHTML = data.phoneNumber;
        document.querySelector("#view_contact_address").innerHTML = data.address;
        document.querySelector("#view_contact_description").innerHTML = data.description;
        openContactModal();
    } catch (error) {
        console.log(error);
    }


    // fetch(`http://localhost:8081/api/contacts/${id}`)
    // .then(async(res) => {
    //     const data = await res.json();
    //     console.log(data);
    // }).catch((error) => {
    //     console.log(error);
    // });

    
    // console.log(data);
}


async function deleteContact(id){
    // const Swal = require('sweetalert2');
    
    Swal.fire({
        title: "Are you sure?",
        text: `You won't be able to revert this!`,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = `${usercontrollerUrl}/delete/${id}`;
            Swal.fire({
                title: "Deleted!",
                text: "Your Contact has been deleted.",
                icon: "success"
            });
        }
    });
}

