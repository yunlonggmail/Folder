# Mac Enter passphrase for key '_*_/.ssh/id_rsa'

解决方式如下

On Mac OSX you can add your private key to the keychain using the command:

```
    ssh-add -K /path/to/private_key
```

If your private key is stored at ~/.ssh and is named id_rsa:

```
    ssh-add -K ~/.ssh/id_rsa
```

You will then be prompted for your password, which will be stored in your keychain.
