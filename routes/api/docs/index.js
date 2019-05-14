const express = require('express');
const router = express.Router();
const swaggerUiAssetPath = require('swagger-ui-dist').getAbsoluteFSPath();
const path = require('path');

router.get('/', (req, res) => res.redirect('/api/docs/swagger-ui?url=/api/docs/api.yaml'))
    .use('/swagger-ui', express.static(swaggerUiAssetPath))
    .use('/api.yaml', express.static(path.join(__dirname, '/api.yaml')));


module.exports = router;