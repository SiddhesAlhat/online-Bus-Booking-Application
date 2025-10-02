// Common JavaScript functions for Bus Booking System
const API_BASE = 'http://localhost:8080/api';

// Utility Functions
function showAlert(message, type, elementId = 'alertBox') {
    const alert = document.getElementById(elementId);
    if (alert) {
        alert.textContent = message;
        alert.className = `alert ${type}`;
        alert.style.display = 'block';
        setTimeout(() => alert.style.display = 'none', 3000);
    }
}

function showLoading(containerId) {
    const container = document.getElementById(containerId);
    if (container) {
        container.innerHTML = `
            <div class="loading">
                <div class="spinner"></div>
                <p>Loading...</p>
            </div>
        `;
    }
}

function checkAuth() {
    const userPhone = localStorage.getItem('userPhone');
    if (!userPhone) {
        window.location.href = '/login';
        return false;
    }
    return true;
}

function logout() {
    localStorage.clear();
    sessionStorage.clear();
    window.location.href = '/login';
}

// API Helper Functions
async function apiCall(endpoint, method = 'GET', body = null, headers = {}) {
    const userPhone = localStorage.getItem('userPhone');
    
    const config = {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'X-User-Phone': userPhone,
            ...headers
        }
    };

    if (body && method !== 'GET') {
        config.body = JSON.stringify(body);
    }

    try {
        const response = await fetch(`${API_BASE}${endpoint}`, config);
        return await response.json();
    } catch (error) {
        console.error('API Error:', error);
        throw error;
    }
}

// Format date
function formatDate(dateString) {
    return new Date(dateString).toLocaleDateString('en-IN', {
        year: 'numeric',
        month: 'short',
        day: 'numeric'
    });
}

// Format time
function formatTime(timeString) {
    return new Date(`2000-01-01T${timeString}`).toLocaleTimeString('en-IN', {
        hour: '2-digit',
        minute: '2-digit'
    });
}

// Format currency
function formatCurrency(amount) {
    return `₹${parseFloat(amount).toFixed(2)}`;
}

// Validate phone number
function isValidPhone(phone) {
    return /^[0-9]{10}$/.test(phone);
}

// Validate email
function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}
