const net = require('node:net');

// Connect to the server
const client = net.createConnection({ port: 8080, host: '127.0.0.1' }, () => {
    console.log('Connected to server!');
    
    // Send initial data
    client.write('Hello Server, I am the client.');
});

// Handle incoming data from the server
client.on('data', (data) => {
    console.log(`Received from server: ${data.toString().trim()}`);
    
    // Close the connection after receiving the echo
    client.end();
});

// Handle disconnection
client.on('end', () => {
    console.log('Disconnected from server.');
});

// Handle connection errors
client.on('error', (err) => {
    console.error(`Client error: ${err.message}`);
});
