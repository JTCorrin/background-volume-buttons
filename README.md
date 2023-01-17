# background-volume-buttons

This plugin listens for and reacts to volume button presses when the app is in the background

## Install

```bash
npm install background-volume-buttons
npx cap sync
```

## API

<docgen-index>

* [`startListening(...)`](#startlistening)
* [`stopListening()`](#stoplistening)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### startListening(...)

```typescript
startListening(options: BackgroundVolumeButtonListenerOptions) => Promise<void>
```

Adds a listener to the volume buttons and fires an event when the volume button is clicked 4 times

| Param         | Type                                                                                                    |
| ------------- | ------------------------------------------------------------------------------------------------------- |
| **`options`** | <code><a href="#backgroundvolumebuttonlisteneroptions">BackgroundVolumeButtonListenerOptions</a></code> |

--------------------


### stopListening()

```typescript
stopListening() => Promise<void>
```

Removes above listener

--------------------


### Interfaces


#### BackgroundVolumeButtonListenerOptions

| Prop               | Type                |
| ------------------ | ------------------- |
| **`triggerCount`** | <code>number</code> |
| **`timeout`**      | <code>number</code> |
| **`listenerName`** | <code>string</code> |

</docgen-api>
