from cryptography.hazmat.primitives.asymmetric import rsa, padding, ec
from cryptography.hazmat.primitives import serialization, hashes
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
from Crypto.Cipher import AES
import os
import base64

class KeyManagementSystem:
    def __init__(self):
        self.symmetric_keys = {}
        self.private_keys = {}
        self.public_keys = {}

    # **1. Generate and Store Symmetric Keys (AES-256)**
    def generate_symmetric_key(self, key_id):
        key = os.urandom(32)  # AES-256 key
        self.symmetric_keys[key_id] = key
        return key

    def encrypt_data_symmetric(self, key_id, plaintext):
        if key_id not in self.symmetric_keys:
            raise ValueError("Key not found")
        
        key = self.symmetric_keys[key_id]
        iv = os.urandom(16)  # AES uses a 16-byte IV
        cipher = AES.new(key, AES.MODE_CBC, iv)
        padded_data = plaintext.ljust(16, " ").encode()  # Simple padding
        ciphertext = cipher.encrypt(padded_data)
        return base64.b64encode(iv + ciphertext).decode()

    def decrypt_data_symmetric(self, key_id, encrypted_text):
        if key_id not in self.symmetric_keys:
            raise ValueError("Key not found")
        
        key = self.symmetric_keys[key_id]
        data = base64.b64decode(encrypted_text)
        iv, ciphertext = data[:16], data[16:]
        cipher = AES.new(key, AES.MODE_CBC, iv)
        return cipher.decrypt(ciphertext).decode().strip()

    # **2. Generate and Manage Asymmetric Key Pairs (RSA/ECC)**
    def generate_asymmetric_key_pair(self, key_id, algorithm="RSA"):
        if algorithm == "RSA":
            private_key = rsa.generate_private_key(
                public_exponent=65537,
                key_size=2048
            )
        elif algorithm == "ECC":
            private_key = ec.generate_private_key(ec.SECP256R1())

        public_key = private_key.public_key()

        self.private_keys[key_id] = private_key
        self.public_keys[key_id] = public_key
        return private_key, public_key

    def encrypt_asymmetric(self, key_id, plaintext):
        if key_id not in self.public_keys:
            raise ValueError("Public key not found")

        public_key = self.public_keys[key_id]
        ciphertext = public_key.encrypt(
            plaintext.encode(),
            padding.OAEP(mgf=padding.MGF1(algorithm=hashes.SHA256()), algorithm=hashes.SHA256(), label=None)
        )
        return base64.b64encode(ciphertext).decode()

    def decrypt_asymmetric(self, key_id, encrypted_text):
        if key_id not in self.private_keys:
            raise ValueError("Private key not found")

        private_key = self.private_keys[key_id]
        ciphertext = base64.b64decode(encrypted_text)
        plaintext = private_key.decrypt(
            ciphertext,
            padding.OAEP(mgf=padding.MGF1(algorithm=hashes.SHA256()), algorithm=hashes.SHA256(), label=None)
        )
        return plaintext.decode()

    # **3. Implement Diffie-Hellman Key Exchange**
    def generate_diffie_hellman_keys(self):
        private_key = ec.generate_private_key(ec.SECP256R1())
        public_key = private_key.public_key()
        return private_key, public_key

    def derive_shared_secret(self, private_key, peer_public_key):
        shared_secret = private_key.exchange(ec.ECDH(), peer_public_key)
        derived_key = PBKDF2HMAC(algorithm=hashes.SHA256(), length=32, salt=b'salt1234', iterations=100000).derive(shared_secret)
        return derived_key

# **Usage Example**
kms = KeyManagementSystem()

# **Symmetric Encryption**
sym_key_id = "session1"
kms.generate_symmetric_key(sym_key_id)
enc_text = kms.encrypt_data_symmetric(sym_key_id, "Hello World")
dec_text = kms.decrypt_data_symmetric(sym_key_id, enc_text)

print("Encrypted (AES):", enc_text)
print("Decrypted (AES):", dec_text)

# **Asymmetric Encryption (RSA)**
asym_key_id = "user1"
kms.generate_asymmetric_key_pair(asym_key_id)
enc_asym = kms.encrypt_asymmetric(asym_key_id, "Hello Asymmetric")
dec_asym = kms.decrypt_asymmetric(asym_key_id, enc_asym)

print("Encrypted (RSA):", enc_asym)
print("Decrypted (RSA):", dec_asym)

# **Diffie-Hellman Key Exchange**
private_A, public_A = kms.generate_diffie_hellman_keys()
private_B, public_B = kms.generate_diffie_hellman_keys()
shared_secret_A = kms.derive_shared_secret(private_A, public_B)
shared_secret_B = kms.derive_shared_secret(private_B, public_A)

print("Shared Secret (A):", base64.b64encode(shared_secret_A).decode())
print("Shared Secret (B):", base64.b64encode(shared_secret_B).decode())
