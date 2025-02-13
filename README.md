# INS_task
This project analyzes three classical encryption techniques: the Playfair, Hill, and Vigenère Ciphers. It examines their encryption methods, complexities, strengths, weaknesses, and cryptanalysis approaches. The discussion includes their vulnerabilities and proposes a hybrid cipher design to address these limitations.
<div> <h3>Hybrid cipher</h3>
  The hybrid cipher in this report merges substitution and transposition methods to create a secure encryption system with a minimum of 128-bit strength. It uses confusion and diffusion principles essential to modern cryptography, making it resilient against various attack strategies.
A Modified Vigenère Cipher is employed for substitution, introducing non-linearity through a key-dependent mechanism that changes plaintext into ciphertext via modular arithmetic. This ensures identical plaintext blocks create distinct ciphertext blocks, reducing frequency analysis risks. However, substitution alone cannot combat statistical pattern attacks.
To enhance security, a Matrix-Based Transposition Layer rearranges substituted blocks based on a key-generated pseudorandom permutation, dispersing the influence of each character across multiple ciphertext blocks and disrupting patterns. The collaboration of substitution and transposition ensures strong non-linearity and resistance to structural analysis.
Guaranteeing 128-bit encryption strength, the cipher operates on large block sizes and uses high-entropy keys. A secure PRNG drives the transposition, allowing for unpredictable block rearrangement. The system's adaptable design permits further security enhancements by increasing block sizes or integrating supplementary cryptographic techniques.
While effective against most classical attacks, potential vulnerabilities may arise from PRNG weaknesses or side-channel attacks. Future upgrades could involve adding modern cryptographic primitives like elliptic curve cryptography or SHA-3. This hybrid method effectively modernizes classical techniques, ensuring secure encryption for today’s applications.
3.1 Cipher Design Process
To create a hybrid cipher with 128-bit encryption, use the following steps:
1.	Substitution Layer: Implement a Modified Vigenère Cipher with energetic, block-based substitutions over a finite field.
2.	Transposition Layer: Use a matrix-based transposition scheme with a PRNG seeded by a secret key.
Steps:
1.	Convert plaintext into numerical blocks for modular arithmetic.
2.	Encrypt each block with the Modified Vigenère Cipher.
3.	Rearrange the blocks using a pseudorandom permutation from the PRNG.
3.2 Encryption and Decryption Examples
Example:
•	Plaintext: "HELLO WORLD"
•	Substitution Key: Key length 10, generated by a secure PRNG.
•	Transposition Key: PRNG seed "MYSECRET123".
Encryption:
1.	Convert "HELLO WORLD" to numerical values.
2.	Substitute using Vigenère: C1[i] = (P[i] + K[i]) mod 256.
3.	Apply transposition with a PRNG-generated matrix.
Decryption:
1.	Reverse the transposition using the PRNG seed.
2.	Reverse the substitution: P[i] = (C1[i] - K[i]) mod 256.
3.3 Security Evaluation
The hybrid cipher combines substitution (confusion) and transposition (diffusion), making it strong against statistical attacks.
Justification:
•	Substitution Weakness: Vulnerable to frequency analysis and key reuse detection.
•	Transposition Weakness: Lacks confusion, making it prone to pattern recognition.
•	Hybrid Strength: The combination enhances resistance to both structural and frequency-based attacks.
3.4 Mathematical Formulation
Let PPP be the plaintext, Ks the substitution key, and Kt the transposition key. The encryption process includes:
Encryption:
1. Substitution:
   C₁[i] = (P[i] + Kₛ[i]) mod 2¹²⁸
2. Transposition:
   C₂ = Permute(C₁, Kₜ)
Decryption:
1. Reverse Transposition:
   C₁ = Unpermute(C₂, Kₜ)
2. Reverse Substitution:
   P[i] = (C₁[i] - Kₛ[i]) mod 2¹²⁸
3.5 Why is the Hybrid Approach More Secure?
1.	Confusion (Substitution):
The Modified Vigenère Cipher uses a energetic substitution, disrupting frequency patterns and thwarting frequency analysis.
2.	Diffusion (Transposition):
The matrix-based transposition spreads plaintext influence across ciphertext blocks, hindering block-based attacks.
3.	Combined Strength:
•	Substitution Weakness: Susceptible to statistical attacks with pattern retention.
•	Transposition Weakness: Lacks complexity to mask plaintext relationships.
•	Hybrid Approach: Combining both methods mitigates their individual weaknesses.

4.	128-bit Encryption Strength:
Using large block sizes and modular arithmetic enhances computational complexity, making brute-force attacks impractical due to the extensive key space.
3.6 Security Justification
The hybrid cipher offers strong security by combining substitution and transposition:
•	Resistance to Frequency Analysis: Substitution alters character frequencies, and transposition obscures ciphertext patterns.
•	High Computational Complexity: Breaking the cipher demands solving large modular equations and reversing pseudorandom permutations, both resource-intensive.
•	Scalability: The cipher can evolve to meet increasing security requirements by adjusting block sizes or integrating more cryptographic primitives.
Conclusion
The hybrid cipher developed in this report offers a novel approach to secure encryption by combining substitution and transposition methods. This integration uses the confusion-diffusion principle, enhancing resistance to both classical and advanced cryptanalysis.
Key Achievements:
1.	Confusion via Substitution: The Modified Vigenère Cipher introduces non-linearity through energetic plaintext block substitutions, using modular arithmetic over a large field to increase complexity and thwart frequency analysis.
2.	Diffusion via Transposition: A matrix-based transposition disrupts ciphertext patterns through pseudorandom block permutations, preventing plaintext reconstruction without the transposition key.
3.	Hybrid Strength: The synergistic use of substitution and transposition obscures the plaintext-ciphertext relationship and eliminates structural patterns in ciphertext, reducing vulnerability to various attacks.
4.	128-bit Encryption Strength: Employing large blocks and high-entropy keys ensures security equivalent to at least 128-bit encryption, suitable for sensitive data protection.

</div>
