
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