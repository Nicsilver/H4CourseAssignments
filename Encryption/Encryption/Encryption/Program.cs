using System;

namespace Encryption
{
    class Program
    {
        static void Main(string[] args)
        {
            Encryptor encryptor = new Encryptor();
            Console.WriteLine("Mikkel Pickle");
            string encryptedText = encryptor.Encrypt("Hola");
            Console.WriteLine(encryptedText);
            string decyptredText = encryptor.Decrypt(encryptedText);
            Console.WriteLine(decyptredText);
        }

    }
}
