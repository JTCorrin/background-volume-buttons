export interface BackgroundVolumeButtonListenerOptions {
  triggerCount: number;
  timeout: number;
  listenerName: string;
}

export interface BackgroundVolumeButtonListenerPlugin {
  /**
   * Adds a listener to the volume buttons and fires an event when the volume button is clicked 4 times
   */
  startListening(options: BackgroundVolumeButtonListenerOptions): Promise<void>;
  /**
   * Removes above listener
   */
  stopListening(): Promise<void>;
}
