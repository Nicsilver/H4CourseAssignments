using System;
using System.Diagnostics;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Threading;

namespace EncryptionGUI
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        EncryptedObject encryptedObject;
        Encryption encryption;
        Stopwatch stopWatch;
        TimeSpan encryptionTime;
        TimeSpan decryptionTime; 
        public MainWindow()
        {
            InitializeComponent();
            stopWatch = new Stopwatch();
            encryption = new Encryption();
            encryptedObject = new EncryptedObject();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            switch (encryption_type.SelectedIndex)
            {
                case 0:
                    encryptedObject.key = encryption.GenerateRandomNumber(8);
                    encryptedObject.iv = encryption.GenerateRandomNumber(8);
                    encryptedObject.encryptionType = 0;
                    break;
                case 1:
                    encryptedObject.key = encryption.GenerateRandomNumber(24);
                    encryptedObject.iv = encryption.GenerateRandomNumber(8);
                    encryptedObject.encryptionType = 1;
                    break;
                case 2:
                    encryptedObject.key = encryption.GenerateRandomNumber(32);
                    encryptedObject.iv = encryption.GenerateRandomNumber(16);
                    encryptedObject.encryptionType = 2;
                    break;

                default:
                    break;
            }
            key_text.Text = string.Concat(encryptedObject.key);
            iv_text.Text = string.Concat(encryptedObject.iv);
        }

        private void encrypt_button_Click(object sender, RoutedEventArgs e)
        {
            byte[] messageArray = Encoding.Default.GetBytes(plaintext_ascii.Text);
            var hexStringPlain = BitConverter.ToString(messageArray);
            hexStringPlain = hexStringPlain.Replace("-", "");
            plaintext_hex.Text = hexStringPlain;
            stopWatch.Start();
            switch (encryptedObject.encryptionType)
            {
                case 0:
                    encryptedObject.message = encryption.DESEncrypt(messageArray, encryptedObject.key, encryptedObject.iv);
                    break;
                case 1:
                    encryptedObject.message = encryption.TripleDESEncrypt(messageArray, encryptedObject.key, encryptedObject.iv);
                    break;
                case 2:
                    encryptedObject.message = encryption.AESEncrypt(messageArray, encryptedObject.key, encryptedObject.iv);
                    break;
                default:
                    break;
            }
            stopWatch.Stop();
            encryptionTime = stopWatch.Elapsed;

            encrypted_ascii.Text = Encoding.ASCII.GetString(encryptedObject.message);
            var hexStringEncrypted = BitConverter.ToString(encryptedObject.message);
            hexStringEncrypted = hexStringEncrypted.Replace("-", "");
            encrypted_hex.Text = hexStringEncrypted;

        }

        private void decrypt_button_Click(object sender, RoutedEventArgs e)
        {
            byte[] decrypted;
            string decryptedMessage = "";
            stopWatch.Start();

            switch (encryptedObject.encryptionType)
            {

                case 0:
                    decrypted = encryption.DESDecrypt(encryptedObject.message, encryptedObject.key, encryptedObject.iv);
                    decryptedMessage = Encoding.UTF8.GetString(decrypted);
                    break;
                case 1:

                    decrypted = encryption.TripleDESDecrypt(encryptedObject.message, encryptedObject.key, encryptedObject.iv);
                    decryptedMessage = Encoding.UTF8.GetString(decrypted);
                    break;
                case 2:
                    decrypted = encryption.AESEDecrypt(encryptedObject.message, encryptedObject.key, encryptedObject.iv);
                    decryptedMessage = Encoding.UTF8.GetString(decrypted);
                    break;
                default:
                    break;
            }
            stopWatch.Stop();
            decryptionTime = stopWatch.Elapsed;
            
            ciphertext_ascii.Text = decryptedMessage;
            byte[] messageArray = Encoding.Default.GetBytes(decryptedMessage);
            var hexStringCipher = BitConverter.ToString(messageArray);
            hexStringCipher = hexStringCipher.Replace("-", "");
            cipertext_hex.Text = hexStringCipher;
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            string foo = "Time taken: " + encryptionTime.ToString(@"m\:ss\.fff");
            encrypt_time.Content = foo;
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            string foo = "Time taken: " + decryptionTime.ToString(@"m\:ss\.fff");
            decrypt_time.Content = foo;
        }
    }
}
