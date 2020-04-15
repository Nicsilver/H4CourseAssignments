using System;
using System.Security.Cryptography;

namespace Encryption_simple
{
    class Program
    {
        static void Main(string[] args)
        {
            using (RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider())
            {
                byte[] data = new byte[4];

                for (int i = 0; i < 10; i++)
                {
                    rng.GetBytes(data);

                    int value = BitConverter.ToInt32(data, 0);
                    Console.WriteLine(value);

                }
            }
        }
    }
}
