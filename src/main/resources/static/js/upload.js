
    document.addEventListener('DOMContentLoaded', function() {
    // Animate summary numbers
    const numbers = document.querySelectorAll('.summary-number');
    numbers.forEach(num => {
    if (!isNaN(num.textContent)) {
    const final = parseInt(num.textContent);
    let current = 0;
    const increment = Math.max(1, Math.ceil(final / 20));

    const timer = setInterval(() => {
    current += increment;
    if (current >= final) {
    current = final;
    clearInterval(timer);
}
    num.textContent = current;
}, 50);
}
});

    // Form submission state
    const form = document.querySelector('form');
    const submitBtn = document.querySelector('.submit-button');

    if (form && submitBtn) {
    form.addEventListener('submit', function() {
    submitBtn.textContent = 'Adding...';
    submitBtn.disabled = true;
});
}
});

    // Add this to your existing upload.js file
    document.addEventListener('DOMContentLoaded', function() {
        // Your existing code...

        // Style status dropdowns based on their current value
        const statusDropdowns = document.querySelectorAll('.status-dropdown');
        statusDropdowns.forEach(dropdown => {
            updateDropdownStyle(dropdown);

            dropdown.addEventListener('change', function() {
                updateDropdownStyle(this);
            });
        });

        function updateDropdownStyle(dropdown) {
            const value = dropdown.value;

            if (value === 'success') {
                dropdown.style.background = '#c6f6d5';
                dropdown.style.color = '#22543d';
                dropdown.style.borderColor = '#9ae6b4';
            } else if (value === 'failure') {
                dropdown.style.background = '#fed7d7';
                dropdown.style.color = '#742a2a';
                dropdown.style.borderColor = '#feb2b2';
            } else {
                dropdown.style.background = '#edf2f7';
                dropdown.style.color = '#4a5568';
                dropdown.style.borderColor = '#e2e8f0';
            }
        }
    });