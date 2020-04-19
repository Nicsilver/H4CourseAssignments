using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EncryptionGUI
{
    class EncryptedObject
    {
        public byte[] message { get; set; }
        public byte[] key { get; set; }
        public byte[] iv { get; set; }
        public int encryptionType { get; set; }
        public EncryptedObject(byte[] message, byte[] key, byte[] iv)
        {
            this.message = message;
            this.key = key;
            this.iv = iv;

        }
        public EncryptedObject()
        {
            this.message = new byte[0];
            this.key = new byte[0];
            this.iv = new byte[0];
            this.encryptionType = -1;
        }
    }
}
