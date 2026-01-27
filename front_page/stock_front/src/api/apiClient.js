// src/api/apiClient.js
const BASE_URL = 'http://localhost:8080';

const handleResponse = async (response) => {
    if (!response.ok) {
        if (response.status === 401) {
            localStorage.removeItem('token');
            window.location.href = '/login';
        }
        throw new Error(`HTTP error! status: ${response.status}`);
    }
    return response.json();
};

const apiClient = {
    get: async (url) => {
        const response = await fetch(`${BASE_URL}${url}`, {
            method: 'GET',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        return handleResponse(response);
    },

    post: async (url, data) => {
        const response = await fetch(`${BASE_URL}${url}`, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
        return handleResponse(response);
    },

    put: async (url, data) => {
        const response = await fetch(`${BASE_URL}${url}`, {
            method: 'PUT',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
        return handleResponse(response);
    },

    delete: async (url) => {
        const response = await fetch(`${BASE_URL}${url}`, {
            method: 'DELETE',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        return handleResponse(response);
    },
};

export default apiClient;