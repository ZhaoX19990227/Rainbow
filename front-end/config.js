(function() {
    let BASE_URL;
    if (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1') {
        BASE_URL = 'http://localhost:8080/api'; // 本地开发环境
    } else {
        BASE_URL = '/api'; // 生产环境
    }
    window.BASE_URL = BASE_URL;
})(); 