using System;
using System.Collections.Generic;
using System.Security.Cryptography;
using System.Text;

namespace Password
{
    class Hash
    {
        public byte[] GenerateSalt()
        {
            int hashByteSize = 32;
            using (RNGCryptoServiceProvider RNGCryptoServiceProvider = new RNGCryptoServiceProvider())
            {
                byte[] salt = new byte[hashByteSize];
                RNGCryptoServiceProvider.GetBytes(salt);

                return salt;
            }
        }

        private byte[] Combine(byte[] first, byte[] second)
        {
            var combinedValues = new byte[first.Length + second.Length];

            Buffer.BlockCopy(first, 0, combinedValues, 0, first.Length);
            Buffer.BlockCopy(second, 0, combinedValues, first.Length, second.Length);

            return combinedValues;
        }

        //Unsure if this is enough, doesnt use HMAC?
        public byte[] GenerateHashedPassword(byte[] password, byte[] salt, int iterations, int hashByteSize)
        {
            using (var rfc2898 = new Rfc2898DeriveBytes(password, salt, iterations))
            {
                return rfc2898.GetBytes(hashByteSize);
            }
        }

        public byte[] hashPasswordWithSaltSha256(byte[] password, byte[] salt, int iterations)
        {
            using (var shar256 = SHA256.Create())
            {
                Console.WriteLine("pre combine" + Convert.ToBase64String(password));
                byte[] passwordHashed  = Combine(password, salt);
                Console.WriteLine("post combine" + Convert.ToBase64String(passwordHashed));

                for (int i = 0; i < iterations; i++)
                {
                    passwordHashed = shar256.ComputeHash(passwordHashed);
                }
                return passwordHashed;
            }
        }

    }
}