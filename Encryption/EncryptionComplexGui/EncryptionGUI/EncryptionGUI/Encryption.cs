using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace EncryptionGUI
{
    class Encryption
    {
        public byte[] GenerateRandomNumber(int length)
        {
            using (var randomNumberGenerator = new RNGCryptoServiceProvider())
            {
                var randomNumber = new byte[length];
                randomNumberGenerator.GetBytes(randomNumber);

                return randomNumber;
            }
        }

        public byte[] AESEncrypt(byte[] message, byte[] key, byte[] iv)
        {
            using (var AesCryptoServiceProvider = new AesCryptoServiceProvider())
            {

                AesCryptoServiceProvider.Key = key;
                AesCryptoServiceProvider.IV = iv;
                AesCryptoServiceProvider.Mode = CipherMode.CBC;
                AesCryptoServiceProvider.Padding = PaddingMode.PKCS7;

                using (var memoryStream = new MemoryStream())
                {
                    var cryptoStream = new CryptoStream(memoryStream, AesCryptoServiceProvider.CreateEncryptor(),
                        CryptoStreamMode.Write);

                    cryptoStream.Write(message, 0, message.Length);
                    cryptoStream.FlushFinalBlock();

                    return memoryStream.ToArray();
                }
            }
        }

        public byte[] AESEDecrypt(byte[] message, byte[] key, byte[] iv)
        {
            using (var AesCryptoServiceProvider = new AesCryptoServiceProvider())
            {
                AesCryptoServiceProvider.Key = key;
                AesCryptoServiceProvider.IV = iv;
                AesCryptoServiceProvider.Mode = CipherMode.CBC;
                AesCryptoServiceProvider.Padding = PaddingMode.PKCS7;

                using (var memoryStream = new MemoryStream())
                {
                    var cryptoStream = new CryptoStream(memoryStream, AesCryptoServiceProvider.CreateDecryptor(),
                        CryptoStreamMode.Write);

                    cryptoStream.Write(message, 0, message.Length);
                    cryptoStream.FlushFinalBlock();
                    
                    return memoryStream.ToArray();
                }
            }
        }

        public byte[] DESEncrypt(byte[] message, byte[] key, byte[] iv)
        {
            using (var DesCryptoServiceProvider = new DESCryptoServiceProvider())
            {

                DesCryptoServiceProvider.Key = key;
                DesCryptoServiceProvider.IV = iv;
                DesCryptoServiceProvider.Mode = CipherMode.CBC;
                DesCryptoServiceProvider.Padding = PaddingMode.PKCS7;

                using (var memoryStream = new MemoryStream())
                {
                    var cryptoStream = new CryptoStream(memoryStream, DesCryptoServiceProvider.CreateEncryptor(),
                        CryptoStreamMode.Write);

                    cryptoStream.Write(message, 0, message.Length);
                    cryptoStream.FlushFinalBlock();

                    return memoryStream.ToArray();
                }
            }
        }

        public byte[] DESDecrypt(byte[] message, byte[] key, byte[] iv)
        {
            using (var DesCryptoServiceProvider = new DESCryptoServiceProvider())
            {
                DesCryptoServiceProvider.Key = key;
                DesCryptoServiceProvider.IV = iv;
                DesCryptoServiceProvider.Mode = CipherMode.CBC;
                DesCryptoServiceProvider.Padding = PaddingMode.PKCS7;

                using (var memoryStream = new MemoryStream())
                {
                    var cryptoStream = new CryptoStream(memoryStream, DesCryptoServiceProvider.CreateDecryptor(),
                        CryptoStreamMode.Write);

                    cryptoStream.Write(message, 0, message.Length);
                    cryptoStream.FlushFinalBlock();

                    return memoryStream.ToArray();
                }
            }
        }
        
        public byte[] TripleDESEncrypt(byte[] message, byte[] key, byte[] iv)
        {
            using (var tripleDESCryptoServiceProvider = new TripleDESCryptoServiceProvider())
            {

                tripleDESCryptoServiceProvider.Key = key;
                tripleDESCryptoServiceProvider.IV = iv;
                tripleDESCryptoServiceProvider.Mode = CipherMode.CBC;
                tripleDESCryptoServiceProvider.Padding = PaddingMode.PKCS7;

                using (var memoryStream = new MemoryStream())
                {
                    var cryptoStream = new CryptoStream(memoryStream, tripleDESCryptoServiceProvider.CreateEncryptor(),
                        CryptoStreamMode.Write);

                    cryptoStream.Write(message, 0, message.Length);
                    cryptoStream.FlushFinalBlock();

                    return memoryStream.ToArray();
                }
            }
        }

        public byte[] TripleDESDecrypt(byte[] message, byte[] key, byte[] iv)
        {
            using (var tirpleDESCryptoServiceProvider = new TripleDESCryptoServiceProvider())
            {
                tirpleDESCryptoServiceProvider.Key = key;
                tirpleDESCryptoServiceProvider.IV = iv;
                tirpleDESCryptoServiceProvider.Mode = CipherMode.CBC;
                tirpleDESCryptoServiceProvider.Padding = PaddingMode.PKCS7;

                using (var memoryStream = new MemoryStream())
                {
                    var cryptoStream = new CryptoStream(memoryStream, tirpleDESCryptoServiceProvider.CreateDecryptor(),
                        CryptoStreamMode.Write);

                    cryptoStream.Write(message, 0, message.Length);
                    cryptoStream.FlushFinalBlock();

                    return memoryStream.ToArray();
                }
            }
        }
    }
}
