using System;
using System.IO;
using System.Text;

namespace Password
{


    //MADE WITH TEXT FILES TO BEGIN WITH WILL BE CHANGED TO SQL LATER
    class Program
    {


        private static int iterations;
        private static Hash hash;

        private static int salt;
        private static byte[] bytearray;

        static void Main(string[] args)
        {


            iterations = 1000;
            hash = new Hash();
            while (true)
            {
                byte[] password = null;
                Console.WriteLine("Press 1 for create user:\n" +
                    "Press 2 for login:\n" +
                    "Press 3: \n" +
                    "");
                switch (Console.ReadKey().Key)
                {
                    case ConsoleKey.D1:
                        Console.WriteLine("Write password");
                        if (File.Exists("login.txt"))
                        {
                            File.Delete("login.txt");
                        }
                        password = Encoding.ASCII.GetBytes(Console.ReadLine());
                        createLogin(password);
                        break;
                    case ConsoleKey.D2:
                        Console.WriteLine("Write password");
                        password = Encoding.ASCII.GetBytes(Console.ReadLine());
                        verifyLogin(password);
                        break;
                    default:
                        break;
                }
            }
        }

        static void createLogin(byte[] password)
        {
            Console.WriteLine(Encoding.UTF8.GetString(password, 0, password.Length)); // this comes out as whatever u write in.
            //Console.WriteLine(Convert.ToBase64String(password)); // this comes out as a weird 
            byte[] salt = hash.GenerateSalt();
            byte[] hashedPassword = hash.hashPasswordWithSaltSha256(password, salt, iterations);
            Console.WriteLine("Salt" + Convert.ToBase64String(salt));

            using (StreamWriter writetext = new StreamWriter("login.txt"))
            {
                Console.WriteLine("HashedPassword: " + Convert.ToBase64String(hashedPassword) + " --- " + "Salt: "+ Convert.ToBase64String(salt));
            }
        }

        static void verifyLogin(byte[] password)
        {
            Console.WriteLine(Convert.ToBase64String(password));

            using (StreamReader streamReader = new StreamReader("login.txt"))
            {
                string hashedPassword = streamReader.ReadLine();
                string salt = streamReader.ReadLine();
                Console.WriteLine("Salt" + salt);

                byte[] reHashedPassword = hash.hashPasswordWithSaltSha256(password, Encoding.ASCII.GetBytes(salt), iterations);

                Console.WriteLine(hashedPassword.ToString() + " : " + salt.ToString());

                Console.WriteLine("hashesPassword: " + hashedPassword);
                Console.WriteLine("rehashed: " + Convert.ToBase64String(reHashedPassword));

                if (Encoding.ASCII.GetBytes(hashedPassword).Equals(reHashedPassword))
                {
                    Console.WriteLine("Logged in");
                }
                else
                {
                    Console.WriteLine("incorect password");
                }
            }


        }



    }
}
