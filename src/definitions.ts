export interface BackgroundVolumeButtonListenerOptions {
  /**
   * The number of times the volume should be changed before the event is fired. Defaults to 3
   */
  triggerCount: number;
  /**
   * The max time in milliseconds before the `triggerCount` resets. Defaults to 1000
   */
  timeout: number;
  /**
   * Required. The string name of the listener you would like the event to broadcast to i.e. `volumeTriggered`
   */
  listenerName: string;
}

export interface BackgroundVolumeButtonListenerPlugin {
  /**
   * Adds a listener to the volume buttons and fires an event when the volume is changed.
   *
   */
  startListening(options: BackgroundVolumeButtonListenerOptions): Promise<void>;
  /**
   * Removes above listener
   */
  stopListening(): Promise<void>;
}
