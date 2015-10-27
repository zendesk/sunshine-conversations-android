# Upgrade guide to Smooch

SupportKit is now Smooch!

Along with the rename, many of the internals have changed, so we're bumping the major version to 2.0.0.

## CDN

To get the new version, simply modify your gradle files to match the new package on JCenter

```
compile 'io.smooch:core:latest.release'
compile 'io.smooch:ui:latest.release'
```

You can still use the old SupportKit package, but note that it will no longer get updated.

## API

The biggest change to the SDK is that the class names have all been switched from SupportKit to Smooch!

For example:

```Java
SupportKit.init(this, "YOUR_APP_TOKEN");
```

Becomes:

```Java
Smooch.init(this, "YOUR_APP_TOKEN");
```

In addition to changing the class names, all previous assets have been changed to have prefix 'Smooch' instead of 'SupportKit' as well.

The rest is pretty much the same!

Enjoy.