# Document Compression using Huffman Coding
This project implements a document compression algorithm using Huffman coding for text files.  
It allows users to select text files from their PC, compress the content, and decompress the compressed data back to the original text.

## Features:

Text File Selection: Browse and select text files for compression/decompression.  

Huffman Coding Algorithm: Utilizes Huffman coding to assign variable-length codes to characters based on their frequency.  

Hash Maps for Efficiency:
Encoding Hash Map: Stores character-to-Huffman code mappings.
Decoding Hash Map: Stores Huffman code-to-character mappings.
## Compression and Decompression:
## Compression:
Reads the text file.
Analyzes character frequencies.
Builds the Huffman tree.
Generates the encoding hash map.
Creates compressed data by replacing characters with their corresponding codes.
## Decompression:
Reads the compressed data.  

Uses the decoding hash map to translate Huffman codes back to characters.  

Reconstructs the original text.  

User Interface: Simple interface for file selection, compression/decompression, and size comparison.  

## Benefits:

Reduces text file storage requirements.  

Enables faster text data transmission.  

Provides a practical implementation of Huffman coding for data compression.  

## Project Files:

Source code implementing Huffman coding and hash maps.
User interface code.
Documentation explaining functionality, algorithms, compilation, and execution.
## Technologies:

Programming Language (java)  

File I/O libraries  

Data structures (Hash Maps, Trees)
## Future Enhancements:

Integration with various file formats.  

Visual representation of the Huffman tree.  

Advanced compression techniques beyond Huffman coding.
