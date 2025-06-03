const isProduction = window.location.hostname === '120.46.13.61';
window.BASE_URL = isProduction ? 'http://120.46.13.61/api' : 'http://localhost:8989/api';

(function() {
    let BASE_URL;
    if (window.location.hostname === 'localhost' || window.location.hostname === '127.0.0.1') {
        BASE_URL = 'http://localhost:8989/api'; // 本地开发环境
    } else {
        BASE_URL = '/api'; // 生产环境
    }
    window.BASE_URL = BASE_URL;
})(); 