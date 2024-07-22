/*This is the JavaScript file for all the webpages for the website "Peacock Nest DayCare Center*/


//***********************************This is for our Index page Js*********************************************


// Wait for the DOM to be fully loaded before running the script
document.addEventListener('DOMContentLoaded', function() {
    // Select the form and subscribe button
    const form = document.getElementById('newsletter-form');
    const subscribeButton = document.getElementById('id-subscribe');

    // Add event listener to the form for submission
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Get the email input value
        const emailInput = document.getElementById('newsletter-email');
        const email = emailInput.value.trim(); // Trim any extra whitespace

        // Validate email format using a simple regex
        if (!isValidEmail(email)) {
            alert('Please enter a valid email address.');
            return;
        }

        // Simulate sending data to server (replace with actual API call if needed)
        // For demonstration purposes, we'll log the email to console
        console.log('Email submitted:', email);

        // Optionally, clear the email input after successful submission
        emailInput.value = '';

        // Inform the user about successful subscription (or handle accordingly)
        alert('Thank you for subscribing!');

        // Additional logic can be added here such as sending data to a server via fetch()

        // You can redirect or perform other actions after successful subscription
    });

    // Function to validate email using a simple regex
    function isValidEmail(email) {
        // Basic email validation using regex
        const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return regex.test(email);
    }
});


//***********************************This is for our contact page Js*********************************************


// Wait for the DOM to be fully loaded before running the script
document.addEventListener('DOMContentLoaded', function() {
    // Select the form and submit button
    const form = document.getElementById('contact-form');
    const submitButton = form.querySelector('button[type="submit"]');

    // Add event listener to the form for submission
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Get input values
        const name = form.querySelector('#name').value.trim();
        const email = form.querySelector('#email').value.trim();
        const message = form.querySelector('#message').value.trim();

        // Validate inputs (basic validation for demonstration)
        if (!name || !email || !message) {
            alert('Please fill in all fields.');
            return;
        }

        // Simulate sending data to server (replace with actual API call if needed)
        // For demonstration purposes, we'll log the data to console
        console.log('Form submitted:');
        console.log('Name:', name);
        console.log('Email:', email);
        console.log('Message:', message);

        // Optionally, clear the form fields after successful submission
        form.reset();

        // Inform the user about successful submission (or handle accordingly)
        alert('Message sent successfully! We will get back to you soon.');

        // Additional logic can be added here such as sending data to a server via fetch()

        // You can redirect or perform other actions after successful submission
    });
});


//***********************************This is for our calculate page Js*********************************************

// Wait for the DOM content to be fully loaded before executing the script
document.addEventListener('DOMContentLoaded', function() {
    // Selecting elements from the DOM
    const form = document.getElementById('eligibility-form'); // The main form element
    const ageInput = document.getElementById('age'); // Input field for child's age
    const daysSelect = document.getElementById('days'); // Dropdown for selecting days of care
    const careTypeSelect = document.getElementById('care-type'); // Dropdown for selecting care type
    const siblingCheckbox = document.getElementById('sibling'); // Checkbox indicating if there's a sibling
    const resultDiv = document.getElementById('result'); // Div to display eligibility result and information

    // Static data for fees based on number of days
    const fees = {
        1: 1500,
        2: 2500,
        3: 3800,
        4: 4500,
        5: 5500
    };

    // Static data for services provided based on care type
    const services = {
        'full-time': [
            'Consistent care: Provides regular care throughout the workweek (often M-F).',
            'Structured routines: Offers daily routines and activities for development.',
            'Enrollment discounts: May offer discounts for full-time commitment.'
        ],
        'part-time': [
            'Flexible scheduling: Choose days and hours that fit your needs.',
            'Ideal for parents with non-traditional work schedules.',
            'Occasional childcare needs. Minimum hours may apply.'
        ],
        'after-school': [
            'Extended care: Provides supervision after school hours until pick-up.',
            'Snack time: Includes a healthy snack for hungry kids.',
            'Homework help: Offers assistance with schoolwork (if needed).',
            'Supervised play: Provides a safe and fun environment for playtime.',
            'Offered by: Often available at daycare centers, schools, or community centers.'
        ]
    };

    // Event listener for form submission
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission behavior

        // Retrieve values from form inputs
        const age = parseInt(ageInput.value); // Child's age
        const days = parseInt(daysSelect.value); // Number of days of care
        const careType = careTypeSelect.value; // Type of care selected (full-time, part-time, after-school)
        const hasSibling = siblingCheckbox.checked; // Whether there is a sibling enrolled

        // Validate the age input using a regular expression
        if (!isValidAge(age)) {
            resultDiv.innerHTML = '<p class="error">Please give a correct age.</p>';
            return;
        }

        // Check if the age is within the eligible range for the daycare program
        if (age < 6 || age > 144) {
            resultDiv.innerHTML = '<p class="error">Child is not eligible for this daycare program.</p>';
            return;
        }

        // Calculate the total fee based on selected days and sibling discount
        let totalFee = calculateTotalFee(days, hasSibling);

        // Generate a list of services based on the selected care type
        let servicesList = generateServicesList(careType);

        // Display the result in the resultDiv
        resultDiv.innerHTML = `
            <p>Child is eligible for this daycare program.</p>
            <p>Total Fee: Rs.${totalFee.toFixed(2)}</p>
            <h3>Services Provided:</h3>
            <ul>${servicesList}</ul>
        `;
    });

    // Function to validate the age input using a regular expression
    function isValidAge(age) {
        const regex = /^[1-9][0-9]?$/; // Matches ages from 1 to 99
        return regex.test(age);
    }

    // Function to calculate the total fee considering optional sibling discount
    function calculateTotalFee(days, hasSibling) {
        let totalFee = fees[days]; // Base fee based on selected days
        if (hasSibling) {
            totalFee *= 0.7; // Apply a 30% discount if there is a sibling enrolled
        }
        return totalFee;
    }

    // Function to generate a list of services based on the selected care type
    function generateServicesList(careType) {
        let servicesList = '';
        services[careType].forEach(service => {
            servicesList += `<li>${service}</li>`; // Build a list item for each service
        });
        return servicesList;
    }
});
