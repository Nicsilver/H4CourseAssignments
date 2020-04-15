using System;
using System.Collections.Generic;
using System.Text;

namespace Encryption
{
    public class Encryptor
    {
        public string Encrypt(String text)
        {
            string encryptedString = "";
            int letter;
            for (int i = 0; i < text.Length; i++)
            {
                letter = (int)text[i] + 1;
                encryptedString += ((char)letter).ToString();
            }
            return encryptedString;
        }

        public string Decrypt(string text)
        {
            string encryptedString = "";
            int letter;
            for (int i = 0; i < text.Length; i++)
            {
                letter = (int)text[i] - 1;
                encryptedString += ((char)letter).ToString();
            }
            return encryptedString;
        }


    }




}
