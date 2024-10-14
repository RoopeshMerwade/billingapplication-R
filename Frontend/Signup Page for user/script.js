const signupForm = document.getElementsByClassName("signup-form")[0];
const email = document.getElementById("email");
const password = document.getElementById("password"); 
const confirmPassword = document.getElementById("confirm-password");
const power = document.getElementById("power-point"); 

const passwordStrength = function () { 
    let point = 0;
    let value = password.value;
    let widthPower =  
        ["1%", "25%", "50%", "75%", "100%"]; 
    let colorPower =  
        ["#D73F40", "#DC6551", "#F2B84F", "#BDE952", "#3ba62f"]; 
  
    if (value.length >= 6) { 
        let arrayTest =  
            [/[0-9]/, /[a-z]/, /[A-Z]/, /[^0-9a-zA-Z]/]; 
        arrayTest.forEach((item) => { 
            if (item.test(value)) { 
                point += 1; 
            }
        }); 
    }

    power.style.width = widthPower[point]; 
    power.style.backgroundColor = colorPower[point];
};

// inspired from geeks from geeks
password.addEventListener('keyup', passwordStrength);

signupForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  
  const data = {
    email: email.value,
    password: password.value,
  };

  try {
    const response = await fetch('http://localhost:8080/api/signup', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });
    
    if (response.ok) {
      // Handle successful response
      alert('Signup successful');
    } else {
      // Handle error response
      alert('Signup failed');
    }
  } catch (error) {
    console.error('Error:', error);
    alert('An error occurred');
  }
});

// check password strength
function onChange() {
  if (confirmPassword.value === password.value) {
    confirmPassword.setCustomValidity("");
  } else {
    confirmPassword.setCustomValidity("Passwords do not match!");
  }
}

password.addEventListener("change", onChange);
confirmPassword.addEventListener("change", onChange);



