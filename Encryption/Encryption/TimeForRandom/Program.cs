using System;
using System.Diagnostics;
using System.Security.Cryptography;

namespace TimeForRandom
{
    class Program
    {
        static void Main(string[] args)
        {
            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start();
            testRandom();
            stopWatch.Stop();
            TimeSpan timeSpanRandom = stopWatch.Elapsed;
            Console.WriteLine("randomTime: {0} ", timeSpanRandom.TotalMilliseconds);

            stopWatch.Start();
            testRNGCrypto();
            stopWatch.Stop();
            TimeSpan timeSpanRNGCrypto = stopWatch.Elapsed;
            Console.WriteLine("RNGCrypto time: {0} ", timeSpanRNGCrypto.TotalMilliseconds);
        }

        static void testRandom()
        {
            var random = new Random();
            for (int i = 0; i < 100; i++)
            {
                Console.WriteLine("{0}", random.Next());
            }
        }
        static void testRNGCrypto()
        {
            using (RNGCryptoServiceProvider provider = new RNGCryptoServiceProvider())
            {
                var byteRNGArray = new byte[4];
                for (int i = 0; i < 100; i++)
                {
                    provider.GetBytes(byteRNGArray);
                    var randomInteger = BitConverter.ToUInt32(byteRNGArray, 0);
                    Console.WriteLine(randomInteger.ToString());
                }
            }
        }
    }
}
